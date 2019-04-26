FROM node:7-alpine

RUN apk add -U subversion
ENV multistage=1
