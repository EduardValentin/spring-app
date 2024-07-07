# command to connect to the running database container
docker-compose exec -it db /bin/bash -c "PGPASSWORD=postgres psql -p 5432 -U postgres -h db -d db"