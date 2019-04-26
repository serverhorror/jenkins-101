FROM golang:latest

RUN pwd
RUN mkdir /src
WORKDIR /src
RUN pwd
COPY . .
RUN CGO_ENABLED=0  go build -o ./return main.go


FROM busybox:latest

COPY --from=0 /src/return ./return
CMD ["/return"]
