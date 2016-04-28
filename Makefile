BUILD_SCRIPT := meta-baylibre/build.sh

# Set number of threads
NUM_THREADS ?= 9

COMMON_ARGS := ${BUILD_SCRIPT} \
                                -p poky/ \
                                -o meta-openembedded/ \
                                -l meta-baylibre \
                                -x "kernel/.git" \
                                -j $(NUM_THREADS) \
                                -t $(NUM_THREADS)

# Machine: swi-mdm9x28

COMMON_BBB := \
                ${COMMON_ARGS} \
                -m beaglebone

COMMON_BIN := \
                ${COMMON_BBB} \
                -b build_bin \
                -q


all: image_bin

clean:
	rm -rf build_*

image_bin:
	$(COMMON_BIN)

COMMON_VIRT_ARM := \
    $(COMMON_ARGS) \
    -m acme-virt-arm \
    -b build_virt-arm

image_virt_arm:
	$(COMMON_VIRT_ARM) -d

qemuarm:
	. poky/oe-init-build-env build_virt-arm
	runqemu $(QEMU_OPTS)
