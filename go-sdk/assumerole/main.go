package main

import (
	"fmt"
	"os"

	"github.com/ucloud/ucloud-sdk-go/services/sts"
	"github.com/ucloud/ucloud-sdk-go/ucloud"
	"github.com/ucloud/ucloud-sdk-go/ucloud/auth"
	"github.com/ucloud/ucloud-sdk-go/ucloud/log"
)

func main() {
	cfg, credential := loadConfig()
	stsClient := sts.NewClient(cfg, credential)
	var assumeRoleRequest sts.AssumeRoleRequest
	assumeRoleRequest.RoleUrn = ucloud.String("ucs:iam::65906048:role/test")
	assumeRoleRequest.RoleSessionName = ucloud.String("test-session")
	assumeRoleResponse, err := stsClient.AssumeRole(&assumeRoleRequest)
	if err != nil {
		panic(err)
	}
	fmt.Println(assumeRoleResponse.Credentials.AccessKeyId)
}

func loadConfig() (*ucloud.Config, *auth.Credential) {
	cfg := ucloud.NewConfig()
	cfg.LogLevel = log.DebugLevel

	credential := auth.NewCredential()
	credential.PrivateKey = os.Getenv("UCLOUD_PRIVATE_KEY")
	credential.PublicKey = os.Getenv("UCLOUD_PUBLIC_KEY")

	log.Info("setup clients ...")

	return &cfg, &credential
}
