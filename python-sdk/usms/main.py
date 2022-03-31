import os

from ucloud.core import exc
from ucloud.client import Client

client = Client({
    "public_key": os.getenv("UCLOUD_PUBLIC_KEY"),
    "private_key": os.getenv("UCLOUD_PRIVATE_KEY"),
    "project_id": os.getenv("UCLOUD_PROJECT_ID"),
})

try:
    resp = client.usms().send_usms_message({
        'PhoneNumbers': [os.getenv("UCLOUD_USMS_PHONE_NUMBER")],
        'SigContent': "UCloud",
        'TemplateId': os.getenv("UCLOUD_USMS_TEMPLATE_ID"),
        'TemplateParams': [os.getenv("UCLOUD_USMS_TEMPLATE_PARAM")],
    })
except exc.UCloudException as e:
    print(e)
else:
    print(resp)

