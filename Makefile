BUILD_SCRIPT := meta-baylibre/build.sh

# Set number of threads
NUM_THREADS ?= 16

COMMON_ARGS := ${BUILD_SCRIPT} \
                                -p poky/ \
                                -o meta-openembedded/ \
                                -l meta-baylibre \
                                -x "kernel/.git" \
                                -j $(NUM_THREADS) \
                                -t $(NUM_THREADS)

COMMON_BIN := \
                ${COMMON_ARGS} \
                -m beaglebone \
                -b build \

all: image_bin

clean:
	rm -rf build_*

image_bin:
	$(COMMON_BIN)

sdcard:
	cd build && ../poky/scripts/wic create ../meta-baylibre/sdimage-bootpart.wks -e baylibre-acme-image
