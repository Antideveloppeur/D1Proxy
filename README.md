<a href="https://github.com/jordanamr/D1Proxy"><img src="https://i.imgur.com/atjAaha.jpg" width="33%" title="Art by @mofumanju" alt="Art by @mofumanju"></a>

# D1Proxy

> A simple yet powerful Java 11 TCP MITM proxy for Dofus Retro (1.30+)

[![Maintainability](https://img.shields.io/codeclimate/maintainability/jordanamr/D1Proxy.svg?style=flat-square)](https://codeclimate.com/github/jordanamr/D1Proxy/maintainability) [![Dependencies](https://img.shields.io/librariesio/github/jordanamr/D1Proxy.svg?style=flat-square)](http://libraries.io/github/jordanamr/D1Proxy) [![Github Issues](https://img.shields.io/github/issues-raw/jordanamr/D1Proxy.svg?style=flat-square)](https://github.com/jordanamr/D1Proxy/issues) [![Pending Pull-Requests](https://img.shields.io/github/issues-pr-raw/jordanamr/D1Proxy.svg?style=flat-square)](https://github.com/jordanamr/D1Proxy/pulls) [![License](https://img.shields.io/github/license/jordanamr/D1Proxy.svg?style=flat-square)](LICENSE)

[![D1Proxy](https://i.imgur.com/G93dU6d.png)](#)

---

## Table of Contents

- [Installation](#installation)
- [Features](#features)
- [Contributing](#contributing)
- [Team](#team)
- [FAQ](#faq)
- [Support](#support)
- [License](#license)
- [Legality](#legality)

---

## Installation

- *(optional)* [Install a MongoDB server](https://docs.mongodb.com/manual/installation/) on your system
- Make sure you have an IDE that includes the Java 11 Developer Kit and Maven

### Clone

- Clone this repo to your local machine using `https://github.com/jordanamr/D1Proxy.git`

### Compile

- Import the project using the pom.xml into your favorite IDE and run a Maven `package` goal
- You will find the jar file inside the `target` folder, named `d1proxy-<version>.jar`

### Setup

- Make sure the `d1proxy.properties` configuration file is in the same folder as your jar file
- Configure the proxy as needed
- Run the Proxy with Java 11

---

## Features

- **Useful commands**
- *(currently)* **Undetected** by Ankama (but use at your own risk)
- **Community-based** data sniffing
- **Quality of Life** features to improve players experience
- A **Plugin system** *(Beta)*

---

## Contributing

> To get started...

### Step 1

- **Option 1**
    - 🍴 Fork this repo!

- **Option 2**
    - 👯 Clone this repo to your local machine using `https://github.com/jordanamr/D1Proxy.git`

### Step 2

- **HACK AWAY!** 🔨🔨🔨

### Step 3

- 🔃 Create a new pull request using <a href="https://github.com/jordanamr/D1Proxy/compare/" target="_blank">`https://github.com/jordanamr/D1Proxy/compare/`</a>.

---

## Team

| <a href="https://github.com/jordanamr" target="_blank">**jordanamr**</a> |
| :---: |
| [![jordanamr](https://avatars1.githubusercontent.com/u/55905781?v=3&s=200)](https://github.com/jordanamr) |
| <a href="https://github.com/jordanamr" target="_blank">`github.com/jordanamr`</a> |

---

## FAQ

- **How to disable the community sniffing features?**
    - Set `proxy.sniffing` to `false`
- **How to run the proxy without MongoDB?**
    - Set `mongo.enabled` to `false`
    - Please understand that disabling MongoDB will also disable the community sniffing features. 
- **How can people connect to my proxy?**
    - 1) Make sure `proxy.ip` is set on your WAN IP address
    - 2) Make sure the `proxy.port` provided is not blocked by your router and/or firewall
    - 3) Distribute them a config.xml that includes a corresponding connserver value.

---

## Support

Reach out to me at one of the following places!

- Twitter at <a href="http://twitter.com/jordan_amr" target="_blank">`@jordan_amr`</a>
- Discord at `jordanamr#4072`

---

## License

[![License](https://img.shields.io/github/license/jordanamr/D1Proxy.svg?style=flat-square)](LICENSE)

- **[AGPL-3.0 license](https://opensource.org/licenses/AGPL-3.0)**
- Copyright 2018-2019 © <a href="http://github.com/jordanamr" target="_blank">jordanamr</a>.

## Legality
> De nombreux éditeurs de logiciels propriétaires incluent dans leurs CLUF des clauses interdisant la rétro-ingénierie. Cependant dans de nombreux pays la rétro-ingénierie est autorisée par la loi, notamment à des fins d'interopérabilité. Dans ces pays, les clauses de ces CLUF ne sont pas valables, ou tout au plus dans les limites déterminées par la loi. Par exemple en France, ce droit est garanti par l'article L122-6-1 du code de la propriété intellectuelle. On trouve des dispositions similaires dans la directive 2009/24/CE du Parlement européen et du Conseil du 23 avril 2009.
