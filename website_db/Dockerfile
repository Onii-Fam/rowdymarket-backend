FROM mysql:8.0

# Set MySQL environment variables
ENV MYSQL_ROOT_PASSWORD=12345
ENV MYSQL_DATABASE=ecom_db

# Copy the SQL script to initialize the database
COPY ./init.sql /docker-entrypoint-initdb.d/

