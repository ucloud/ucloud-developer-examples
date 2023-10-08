import os

from ucloud.core import exc
from ucloud.client import Client

client = Client({
    "public_key": os.getenv("UCLOUD_PUBLIC_KEY"),
    "private_key": os.getenv("UCLOUD_PRIVATE_KEY"),
    "project_id": os.getenv("UCLOUD_PROJECT_ID"),
})

try:
    resp = client.uvms().send_uvms_message({
        'CalledNumber': os.getenv("UCLOUD_UVMS_PHONE_NUMBER"),
        'TemplateId': os.getenv("UCLOUD_UVMS_TEMPLATE_ID"),
        'TemplateParams': [os.getenv("UCLOUD_UVMS_TEMPLATE_PARAM")],
    })
except exc.UCloudException as e:
    print(e)
else:
    print(resp)

