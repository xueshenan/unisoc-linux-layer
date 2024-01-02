SUMMARY = "Stress scripts used for burning"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://start.py \
           file://disk.py \
           file://runner.py \
           file://read_temp.py \
           file://read_oops.py \
"

S = "${WORKDIR}"

do_install() {
	install -d ${D}${libdir}
	install -d ${D}${libdir}/stress-cicd
	install -m 0755 ${WORKDIR}/start.py ${D}${libdir}/stress-cicd/start.py
	install -m 0644 ${WORKDIR}/disk.py ${D}${libdir}/stress-cicd/disk.py
	install -m 0644 ${WORKDIR}/runner.py ${D}${libdir}/stress-cicd/runner.py
	install -m 0755 ${WORKDIR}/read_temp.py ${D}${libdir}/stress-cicd/read_temp.py
	install -m 0755 ${WORKDIR}/read_oops.py ${D}${libdir}/stress-cicd/read_oops.py
}

RDEPENDS_${PN} = " \
	iperf3 \
	fio \
	stress-ng \
	sysstat \
	gstreamer1.0-plugins-good-video4linux2 \
	gstreamer1.0-plugins-base-playback \
"

FILES_${PN} = " \
	${libdir}/stress-cicd/start.py \
	${libdir}/stress-cicd/disk.py \
	${libdir}/stress-cicd/runner.py \
	${libdir}/stress-cicd/read_temp.py \
	${libdir}/stress-cicd/read_oops.py \
"

