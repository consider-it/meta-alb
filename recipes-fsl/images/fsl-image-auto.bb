#
# Copyright 2017-2018 NXP
#

require recipes-fsl/images/fsl-image-base.bb
require recipes-fsl/images/fsl-image-core-common.inc
include recipes-fsl/images/fsl-image-s32-common.inc

inherit distro_features_check

# copy the manifest and the license text for each package to image
COPY_LIC_MANIFEST = "1"
COPY_LIC_DIRS = "1"

IMAGE_INSTALL += " \
    kernel-devicetree \
    packagegroup-core-buildessential \
    packagegroup-core-nfs-server \
    packagegroup-core-tools-debug \
"

# Benchmark tools
IMAGE_INSTALL += "dhrystone"

# Add packages required for OpenMPI demo
# TODO: add them to the RDEPENDS list in the OpenMPI demo recipe
IMAGE_INSTALL += "imagemagick gnuplot mpich mpich-dev"

# Supporting complex evaluation scenarios
IMAGE_INSTALL += "openssl-misc openssl openssl-dev libcrypto libssl openssl-conf openssl-engines openssl-bin"
IMAGE_INSTALL_remove += "ipsec-tools"

# Increase the freespace
IMAGE_ROOTFS_EXTRA_SPACE ?= "54000"

# Enable LXC features.
# On LS2 enable it by default. On s32, only by DISTRO_FEATURE
LXC_INSTALL_PACKAGES = "lxc debootstrap"
IMAGE_INSTALL_append_s32 = "${@bb.utils.contains('DISTRO_FEATURES', 'lxc', ' ${LXC_INSTALL_PACKAGES}', '', d)}"
IMAGE_INSTALL_append_ls2 = " ${LXC_INSTALL_PACKAGES}"

# SFTP server
IMAGE_INSTALL_append = " openssh openssh-sftp openssh-sftp-server "

# Enable Xen and add Xen Packages
require ${@bb.utils.contains('DISTRO_FEATURES', 'xen', 'recipes-fsl/images/fsl-image-xen.inc', '', d)}
