variable "region" {
    description = "AWS Region to use"
    type = string
    default = "ap-northeast-1"
}

variable "environment" {
    description = "Environment"
    type = string
    default = "test"
}

variable "name" {
    description = "Name to use for resources"
    type = string
    default = "adview"
}

variable "organization" {
    description = "Github organization to use"
    type = string
    default = "Ryoto-Noguchi"
}

variable "circleci_cli_token" {
    description = "CircleCI CLI Token to use"
    type = string
}

variable "tags" {
    description = "tags"
    type = map(string)

    default = {
        "managed_by" = "terraform"
    }
}
