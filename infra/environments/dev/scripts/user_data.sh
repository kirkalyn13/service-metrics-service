#!/bin/bash
set -e

yum update -y
amazon-linux-extras enable corretto17
yum install -y java-17-amazon-corretto awscli

# Build .env from Secrets Manager (by ARN) + Parameter Store (by name)
ENV_FILE="${app_jar_path}/.env"
mkdir -p "${app_jar_path}"
> "$ENV_FILE"

%{ for key, arn in secret_arns ~}
value=$(aws secretsmanager get-secret-value \
  --secret-id "${arn}" \
  --query SecretString --output text \
  --region "${aws_region}")
echo "$(echo ${key} | tr a-z A-Z)=$value" >> "$ENV_FILE"
%{ endfor ~}

%{ for key, name in parameter_names ~}
value=$(aws ssm get-parameter \
  --name "${name}" \
  --query Parameter.Value --output text \
  --region "${aws_region}")
echo "$(echo ${key} | tr a-z A-Z)=$value" >> "$ENV_FILE"
%{ endfor ~}

nohup java -jar "${app_jar_path}" \
  --server.port=${app_port} \
  --app.api-key="$API_KEY" \
  > /var/log/app.log 2>&1 &