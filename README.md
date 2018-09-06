
## resource

[swagger ui](http://127.0.0.1:8080/swagger-ui.html)

[swagger schema](http://127.0.0.1:8080/v2/api-docs)

## sh

```
cd $project_folder
sh sh/start-local.sh
cp s2i.gitignore .gitignore

mvn clean package -DskipTests
```

## git

```
git clone https://intra.sustools.ebc.gov.on.ca/bitbucket/scm/soit/s2i_common.git

git clone https://intra.sustools.ebc.gov.on.ca/bitbucket/scm/soit/s2i_digital_enterprise_notification_service.git
```

## docker
```
docker build -t "s2i/den-service:v1" .
```
