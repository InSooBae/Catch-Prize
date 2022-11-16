FROM node:18-alpine3.15
ARG HELLO_PORT
WORKDIR /workspace
COPY ./ /workspace
RUN npm install
ENV HELLO_PORT=${HELLO_PORT}
EXPOSE ${HELLO_PORT}
ENTRYPOINT [ "node", "app.js" ]
