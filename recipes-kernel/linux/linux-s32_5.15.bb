PV = "5.15.145"

require recipes-kernel/linux/linux-s32.inc

SRCREV = "e7f088ac69044656122498194a1b55eb514ef4f9"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

PV_MAJ_VER = "${@oe.utils.trim_version("${PV}", 2)}"
