# Copyright 2017,2018 NXP

SUMMARY = "Add support for SJA1105 switch for S32V234EVB and BB Mini"
LICENSE = "GPLv2+ & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit module

SRC_URI = "git://source.codeaurora.org/external/autobsps32/sja1105x;branch=alb/master;protocol=https"
SRCREV = "42cf3ba62848725f0fb80a65b95062e2d279dd46"

S = "${WORKDIR}/git"
DESTDIR = "${D}"
EXTRA_OEMAKE_append = " INSTALL_DIR=${DESTDIR} KERNELDIR=${KBUILD_OUTPUT} MYCOMPILER=${CROSS_COMPILE}gcc "
EXTRA_OEMAKE_append_s32v234evb = " MYPLATFORM=evb "
EXTRA_OEMAKE_append_s32v234bbmini = " MYPLATFORM=bbmini "

do_install_append() {
	install -d ${D}/${sysconfdir}/init.d
	install -d ${D}/${sysconfdir}/rc5.d
	install -d ${D}/${sysconfdir}/rc3.d
	install -m 755 ${WORKDIR}/sja1105.sh ${D}/${sysconfdir}/init.d/sja1105.sh
	echo "lsmod | grep -q \"^nxp \"  > /dev/null || insmod /lib/modules/\`uname -r\`/kernel/drivers/net/phy/nxp/nxp.ko" >> ${D}/${sysconfdir}/init.d/sja1105.sh
	ln -sf ../init.d/sja1105.sh      ${D}${sysconfdir}/rc5.d/S90sja1105.sh
	ln -sf ../init.d/sja1105.sh      ${D}${sysconfdir}/rc3.d/S90sja1105.sh
}

SRC_URI += " \
    file://sja1105.sh \
"

FILES_${PN} += "${base_libdir}/*"
FILES_${PN} += "${sysconfdir}/init.d/*"
FILES_${PN} += "${sysconfdir}/rc5.d/*"
FILES_${PN} += "${sysconfdir}/rc3.d/*"

PROVIDES = "kernel-module-sja1105pqrs${KERNEL_MODULE_PACKAGE_SUFFIX}"
RPROVIDES_${PN} = "kernel-module-sja1105pqrs${KERNEL_MODULE_PACKAGE_SUFFIX}"

COMPATIBLE_MACHINE = "${@bb.utils.contains('PREFERRED_VERSION_linux-s32', '4.14', '', 's32v234evb|s32v234bbmini', d)}"
INHIBIT_PACKAGE_STRIP = "1"

DEPENDS_append = " coreutils-native"
