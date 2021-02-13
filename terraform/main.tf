terraform {
    backend "s3" {
        bucket = "adview-tfstate"
        key = "tf-circle-ecr-demo"
        region = "ap-northeast-1"
    }
    required_providers {
        circleci = {
            source = "mrolla/circleci"
            version = "0.4.0"
        }
        aws = {
          source  = "hashicorp/aws"
          version = "~> 3.0"
        }
    }
}

provider "aws" {
    region = var.region
}

provider "circleci" {
    api_token = var.circleci_cli_token
    organization = var.organization
}
