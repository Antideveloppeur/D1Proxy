package me.antidev.d1proxy.handlers;

import me.antidev.d1proxy.Proxy;
import me.antidev.d1proxy.network.ProxyClient;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.*;
import org.bson.Document;

import java.io.File;
import java.io.FileOutputStream;

@Slf4j
public class GDMHandler implements PacketHandler {

    private final Proxy proxy;

    public GDMHandler(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination) {
        String[] extraData = packet.split("\\|");
        int mapId = Integer.parseInt(extraData[1]);
        String mapDate = extraData[2];
        String mapKey = extraData[3];
        proxyClient.setCurrentMap(mapId);
        if (!proxy.getConfiguration().isProxySniffing() || !proxy.getConfiguration().isMongoEnabled()) return true;
        if (!proxy.getDatabase().getMapsCollection().mapExists(mapId)) {
            proxy.getDatabase().getMapsCollection().insertNewMap(mapId, proxyClient.getUsername(), mapDate, mapKey);
            proxy.sendMessage("<b>" + proxyClient.getUsername() + "</b> a découvert une nouvelle map ! <i>(ID: " + mapId + ")</i>");
        } else {
            String storedMapDate = proxy.getDatabase().getMapsCollection().getMapDate(mapId);
            if (!storedMapDate.equals(mapDate)) {
                proxy.getDatabase().getMapsCollection().updateMap(mapId, new Document().append("date", mapDate).append("key", mapKey));
                proxy.getDatabase().getMapsCollection().deleteFightCells(mapId);
                proxyClient.sendMessage("Cette map <i>(ID: " + mapId + ")</i> a subit une mise à jour de la part d'Ankama, si possible, lancez un combat pour obtenir les cellules de combat à jour.");
                try {
                    deleteMapFile(mapId + "_" + storedMapDate + (mapKey.isBlank() ? ".swf" : "X.swf"));
                } catch (Exception ex) {
                    log.error("An error occurred while deleting a map file.", ex);
                }
            }
        }
        try {
            downloadMapFile(mapId + "_" + mapDate + (mapKey.isBlank() ? ".swf" : "X.swf"));
        } catch (Exception ex) {
            log.error("An error occurred while downloading a map file.", ex);
        }
        return true;
    }

    private void downloadMapFile(String fileName) throws Exception {
        File directory = new File("maps");
        if (!directory.exists() && !directory.mkdir()) {
            throw new Exception("Creation of the maps folder failed");
        }
        File mapFile = new File("maps/" + fileName);
        if (mapFile.exists()) return;
        FileOutputStream stream = new FileOutputStream(mapFile);
        String url = proxy.getConfiguration().getDofusMapsCdn();
        if (!url.endsWith("/")) url = url + "/";
        Dsl.asyncHttpClient().prepareGet(url + fileName).execute(new AsyncCompletionHandler<FileOutputStream>() {
            @Override
            public State onStatusReceived(HttpResponseStatus responseStatus) {
                if (responseStatus.getStatusCode() != 200) {
                    log.error("Response code " + responseStatus.getStatusCode() + " for map request " + fileName);
                    return State.ABORT;
                }
                return State.CONTINUE;
            }

            @Override
            public State onBodyPartReceived(HttpResponseBodyPart bodyPart) throws Exception {
                stream.getChannel().write(bodyPart.getBodyByteBuffer());
                return State.CONTINUE;
            }

            @Override
            public FileOutputStream onCompleted(Response response) {
                try {
                    stream.close();
                } catch (Exception ex) {
                    log.error("An error occurred while downloading a map file.", ex);
                    return stream;
                }
                log.info("Downloaded map file " + fileName);
                return stream;
            }
        });
    }

    private boolean deleteMapFile(String fileName) throws Exception {
        File mapFile = new File("maps/" + fileName);
        return mapFile.delete();
    }
}
