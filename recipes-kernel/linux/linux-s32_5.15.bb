PV = "5.15.153"

require recipes-kernel/linux/linux-s32.inc

SRCREV = "adceb7e8fa72c4f26207356a5a33b55b0838717f"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

PV_MAJ_VER = "${@oe.utils.trim_version("${PV}", 2)}"

SRC_URI:append:s32cc = "${@bb.utils.contains('DISTRO_FEATURES', 'quick-boot', ' file://build/quick-boot_${PV_MAJ_VER}.cfg', '', d)}"
DELTA_KERNEL_DEFCONFIG:append:s32cc = "${@bb.utils.contains('DISTRO_FEATURES', 'quick-boot', ' quick-boot_${PV_MAJ_VER}.cfg', '', d)}"
