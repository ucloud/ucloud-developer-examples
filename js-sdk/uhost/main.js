const {Client} = require("@ucloud/ucloud-sdk-js");

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms))
}

async function main() {
    // Build client
    const client = new Client({
        config: {
            region: 'cn-bj2',
            projectId: process.env.UCLOUD_PROJECT_ID || '',
        },
        credential: {
            publicKey: process.env.UCLOUD_PUBLIC_KEY || '',
            privateKey: process.env.UCLOUD_PRIVATE_KEY || '',
        }
    });
    const zone = "cn-bj2-05";

    let resp = null;

    // Describe Image
    try {
        resp = await client.uhost().describeImage();
    } catch (e) {
        throw e;
    }
    const image = resp["ImageSet"][0];


    // Create Instance
    try {
        resp = await client.uhost().createUHostInstance({
            Name: "sdk-js-example",
            Zone: zone,
            ImageId: image["ImageId"],
            LoginMode: "Password",
            Password: new Buffer("UCloud1234!").toString('base64'),
            CPU: 1,
            Memory: 1024,
            Disks: [{
                Size: image["ImageSize"],
                Type: "CLOUD_SSD",
                IsBoot: "true",
            }],
        });
    } catch (e) {
        throw e;
    }
    const id = resp["UHostIds"][0];

    // Wait instance to boot
    while (true) {
        try {
            resp = await client.uhost().describeUHostInstance({
                Zone: zone,
                UHostIds: [id],
            });
        } catch (e) {
            break;
        }
        await sleep(3000);
        const instance = resp["UHostSet"][0];
        console.log(`waiting ${id} to boot, got ${instance["State"]}`);
        if (["Running"].includes(instance["State"])) {
            break;
        }
    }

    // Stop the instance
    try {
        resp = await client.uhost().stopUHostInstance({
            Zone: zone,
            UHostId: id,
        });
    } catch (e) {
        throw e;
    }

    // Wait instance to boot
    while (true) {
        try {
            resp = await client.uhost().describeUHostInstance({
                Zone: zone,
                UHostIds: [id],
            });
        } catch (e) {
            break;
        }
        await sleep(3000);
        const instance = resp["UHostSet"][0];
        console.log(`waiting ${id} to stopped, got ${instance["State"]}`);
        if (["Stopped"].includes(instance["State"])) {
            break;
        }
    }

    // Cleanup the instance
    try {
        resp = await client.uhost().terminateUHostInstance({
            Zone: zone,
            UHostId: id,
        });
    } catch (e) {
        throw e;
    }
}

main().catch(e => { console.error(e) })

