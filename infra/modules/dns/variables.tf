variable "app_name" {
  type = string
}

variable "domain_name" {
  type    = string
  default = "service-metrics-service.dev"
}

variable "instance_ip" {
  description = "Public IP of the EC2 instance to point the A record at"
  type        = string
}
