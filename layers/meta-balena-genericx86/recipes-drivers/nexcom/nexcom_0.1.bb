SUMMARY = "NEXCOM.io"
DESCRIPTION = "Bitbake recipe for NEXCOM.io"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"

SRCREV = "2cac614434db655c1726f6d0e72efc2e81c2438c"
SRC_URI = "git://github.com/rohitzsh/nexcom-io.git;branch=master;protocol=https"

S="${WORKDIR}/git"

inherit module

do_compile() {
   oe_runmake 'MODPATH="${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/nexcom-io" ' \
               'KDIR="${STAGING_KERNEL_DIR}"' \
               'KERNEL_VERSION="${KERNEL_VERSION}"'
}

do_install() {
   install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/nexcom-io
   install -m 0644 ${S}/c*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/nexcom-io
}

RPROVIDES_${PN} += "kernel-module-nexcom-io"

KERNEL_MODULE_AUTOLOAD += "NEXCOM_IO"