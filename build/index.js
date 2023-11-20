"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const http_1 = require("http");
(0, http_1.createServer)(function (req, res) {
    res.writeHead(200, { 'Content-Type': 'text/html' });
    res.end('Hello World!');
}).listen(8080);
