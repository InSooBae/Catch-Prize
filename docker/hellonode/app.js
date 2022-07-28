const express = require('express')
const app = express()
const port = parseInt(process.env.HELLO_PORT);

app.get('/', (req, res) => {
  res.send(`Hello World! from PORT=${port}`)
});

app.listen(port, () => {
  console.log(`Express application listening on port ${port}`);
})

app.all("*", (req, res, next) => {
        console.log(req.protocol + "://" + req.get("host") + req.originalUrl);
        next();
});
