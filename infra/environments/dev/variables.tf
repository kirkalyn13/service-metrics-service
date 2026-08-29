variable "app_name" {
  type    = string
  default = "service-metrics-service"
}

variable "env" {
  type    = string
  default = "dev"
}

variable "aws_region" {
  type    = string
  default = "us-east-1"
}

variable "vpc_cidr" {
  type    = string
  default = "10.0.0.0/16"
}

variable "ami_id" {
  description = "AMI ID — dummy value is fine for LocalStack"
  type        = string
  default     = "ami-00000000"
}

variable "instance_type" {
  type    = string
  default = "t3.micro"
}

variable "app_port" {
  type    = number
  default = 8080
}

variable "app_jar_path" {
  type    = string
  default = "/opt/app/service-metrics-service.jar"
}

variable "domain_name" {
  type    = string
  default = "service-metrics-service.dev"
}

variable "api_key" {
  description = "API key for the Spring Boot app — set via TF_VAR_api_key"
  type        = string
  sensitive   = true
}

variable "create_public_subnet" {
  type    = bool
  default = false
}

variable "create_private_subnet" {
  type    = bool
  default = true
}
