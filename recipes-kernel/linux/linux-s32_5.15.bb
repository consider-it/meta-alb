PV = "5.15.119"

require recipes-kernel/linux/linux-s32.inc

SRCREV = "5fe0cca765ed07a2f12ed719a82982574a6c8d45"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

PV_MAJ_VER = "${@oe.utils.trim_version("${PV}", 2)}"
