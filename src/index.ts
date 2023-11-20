import { createServer, IncomingMessage, ServerResponse } from 'http';

createServer(function (req:IncomingMessage, res:ServerResponse) {
  res.writeHead(200, {'Content-Type': 'text/html'});
  res.end('Hello World!');
}).listen(8080);
