FILESEXTRAPATH:prepend:ls2080abluebox := "${THISDIR}/${BPN}:"
# BlueBox specific additions/changes
MC_CFG:ls2080abluebox = "ls2080a"
SRC_URI:append:ls2080abluebox = "\
    file://10.20.4/config/ls2080a/RDB/custom/ls2080abluebox-dpl-ethbluebox.0x2A_0x41.dts \
    file://10.20.4/config/ls2080a/RDB/custom/ls2080abluebox-dpc-0x2a41.dts \
"
