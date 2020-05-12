FROM mysql:5.7
ENV MYSQL_DATABASE=mtg_deck
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=password
ENV MYSQL_ROOT_PASSWORD=password
EXPOSE 3306

COPY ./setup.sql /docker-entrypoint-initdb.d/