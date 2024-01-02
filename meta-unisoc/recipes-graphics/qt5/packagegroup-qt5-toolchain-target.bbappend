# These do not build with gcc7 and we do not really have the resources
# to try and fix this with backports and what not.

# Also, webkit is currently not used in the target build anyway and should
# not be needed in the SDK , but it is brought in by this packagegroup as
# it is defined in meta-qt5.
RDEPENDS_${PN}_remove = " qtquick1-dev qtquick1-mkspecs qtquick1-plugins qtquick1-qmlplugins qttranslations-qtquick1 qttranslations-qt"


RDEPENDS_${PN}_remove = " \
    qtwebkit \
    qtwebkit-dev \
    qtwebkit-mkspecs \
    qtwebkit-qmlplugins \
"

