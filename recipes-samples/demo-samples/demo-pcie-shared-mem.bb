LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://pcie_virt_eth/COPYING;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

URL ?= "git://github.com/nxp-auto-linux/alb-demos;protocol=https"
BRANCH ?= "${RELEASE_BASE}"
SRC_URI = "${URL};branch=${BRANCH}"

S = "${WORKDIR}/git"
SRCREV = "3c94cfd0552cc7941a587e022e20dcd85925a471"
SAMPLESDIR = "/opt/samples"
DESTDIR = "${D}${SAMPLESDIR}"

do_install() {
        install -d ${DESTDIR}
        oe_runmake install INSTALLDIR=${DESTDIR}
}

FILES_${PN} = "${SAMPLESDIR}"
FILES_${PN}-dbg += "${SAMPLESDIR}/.debug"

DEMO_PCIE_APPS ?= "pcie_ep pcie_rc"

DEMO_PCIE_APPS_ls2 = "pcie_rc"
DEMO_PCIE_APPS_lx2160a = "pcie_rc"

EXTRA_OEMAKE = "samples=pcie_shared_mem apps="${DEMO_PCIE_APPS}""

require demo-pcie.inc
