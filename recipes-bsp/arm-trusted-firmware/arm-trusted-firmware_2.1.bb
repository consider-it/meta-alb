# Copyright 2019 NXP

DESCRIPTION = "ARM Trusted Firmware"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://license.rst;md5=c709b197e22b81ede21109dbffd5f363"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

# ATF repository
SRC_URI = "git://github.com/nxp-auto-linux/arm-trusted-firmware.git;protocol=https;branch=alb/master"
SRCREV ?= "476f28fdcc2f88f63156e63c61dfbbf510d55b70"

PLATFORM_s32g275sim = "s32g"
BUILD_TYPE = "release"

EXTRA_OEMAKE += "\
                CROSS_COMPILE=${TARGET_PREFIX} \
                ARCH=${TARGET_ARCH} \
                BUILD_BASE=${B} \
                PLAT=${PLATFORM} \
                "

do_compile() {
        unset LDFLAGS
        oe_runmake -C ${S}
}

do_deploy() {
        install -d ${DEPLOY_DIR_IMAGE}
        cp ${B}/${PLATFORM}/${BUILD_TYPE}/bl31.bin ${DEPLOY_DIR_IMAGE}/bl31-${MACHINE}.bin
}

addtask deploy before do_build after do_compile

COMPATIBLE_MACHINE = "s32g275sim"
