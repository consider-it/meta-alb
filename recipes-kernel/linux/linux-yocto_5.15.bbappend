# in order for this bbappend to take effect,
# add the following lines in conf/local.conf:
#
# PREFERRED_PROVIDER_virtual/kernel = "linux-yocto"
# KMACHINE:s32g = "s32g"

COMPATIBLE_MACHINE:s32g = "s32g"

# we do not want to take the linux-yocto from YoctoProject
SRC_URI:remove:s32g = "git://git.yoctoproject.org/linux-yocto.git;name=machine;branch=${KBRANCH};"

KVER = "5.15.119"
PV = "${KVER}"
SRCBRANCH:s32g = "${RELEASE_BASE}-${KVER}-rt"
SRCREV_machine:s32g = "e18f05316cd90bacc5d1b3d5f7caf44e12e2cbf5"

# instead, we will take NXP linux project from GitHub
SRC_URI:append:s32g = "\
	git://github.com/nxp-auto-linux/linux.git;name=machine;protocol=https;branch=${SRCBRANCH};"

KMACHINE:s32g = "s32g"
