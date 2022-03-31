package main

import (
	"fmt"
	"os"

	"github.com/ucloud/ucloud-sdk-go/services/uvms"
	"github.com/ucloud/ucloud-sdk-go/ucloud"
	"github.com/ucloud/ucloud-sdk-go/ucloud/auth"
	"github.com/ucloud/ucloud-sdk-go/ucloud/config"
	"github.com/ucloud/ucloud-sdk-go/ucloud/log"
)

func main() {
	// build config
	cfg := config.NewConfig()
	cfg.LogLevel = log.DebugLevel
	cfg.ProjectId = os.Getenv("UCLOUD_PROJECT_ID")

	credential := auth.NewCredential()
	credential.PrivateKey = os.Getenv("UCLOUD_PRIVATE_KEY")
	credential.PublicKey = os.Getenv("UCLOUD_PUBLIC_KEY")

	client := uvms.NewClient(&cfg, &credential)

	// send request
	req := client.NewSendUVMSMessageRequest()
	req.TemplateId = ucloud.String(os.Getenv("UCLOUD_UVMS_TEMPLATE_ID"))
	req.CalledNumber = ucloud.String(os.Getenv("UCLOUD_USMS_PHONE_NUMBER"))
	req.TemplateParams = []string{
		os.Getenv("UCLOUD_USMS_TEMPLATE_PARAM"),
	}
	resp, err := client.SendUVMSMessage(req)
	if err != nil {
		panic(err)
	}
	fmt.Printf("%+v", resp)
}
