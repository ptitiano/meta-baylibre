# BayLibre meta layer for Poky #

This README file contains information on the contents of the
baylibre layer.

Please see the corresponding sections below for details.

## Layers and sub-layers ##

 * __meta-baylibre__      is the layer for common scripts/classes/ custom recipes.
 * __meta-baylibre-acme__ is the layer for the current beaglebone-based acme board.

## Fetching and preparing ##

Get the repo with:

 * ACME $> repo init -u https://github.com/baylibre-acme/ACME
 * ACME $> repo sync

build with:

 * ACME $> make

clean chosen packages (kernel, u-boot, distro images, acme-utils) with

 * ACME $> make clean 

rebuild everything

 * ACME $> make distclean

## Built kernel and images ##

### Recipes ###

The distro builds a custom kernel pulled from mainline, in
order to have the latest drivers for the power monitoring
chip in use.

 * image name:       baylibre-acme-image
 * kernel recipe:    linux-yocto-mainline
 * DTB file link:    zImage-am335x-boneblack.dtb
 * kernel file link: zImage

### Image Features ###

Noticeable packages are:

 * avahi: this board can be accessed with ssh root@baylibre-acme.local (or with the hostname you set)
 * libiio: iiod is started, so that the board can be operated remotely
 * systemd

## SDcard creation ##

 * make sdcard

then:

```
    sudo dd if=/var/tmp/wic/build/sdimage-bootpart-...-mmcblk.direct of=/dev/sdc bs=1M count=2048
```

## Build Configuration ##

```
Build image of baylibre-acme-image (for beaglebone-acme).

Build Configuration:
BB_VERSION        = "1.30.0"
BUILD_SYS         = "x86_64-linux"
NATIVELSBSTRING   = "Ubuntu-12.04"
TARGET_SYS        = "arm-poky-linux-gnueabi"
MACHINE           = "beaglebone-acme"
DISTRO            = "acme-baylibre"
DISTRO_VERSION    = "2.1"
TUNE_FEATURES     = "arm armv7a vfp  neon        callconvention-hard        cortexa8"
TARGET_FPU        = "hard"
meta              
meta-poky         
meta-yocto-bsp    = "HEAD:8f51f6153a09f8048fb4c4ce9cf4a19655240de4"
meta-oe           = "HEAD:247b1267bbe95719cd4877d2d3cfbaf2a2f4865a"
meta-baylibre-acme 
meta-baylibre     = "HEAD:492388497e510b59defb5e9259fda7b495b4e819"
```

## Tips ##

### Setting the Hostname ###

Edit meta-baylibre-acme/conf/distro/acme-baylibre.conf and change hostname_pn-base-files.

### Partial rebuild ###

Some bitbake tips to rebuild stuff from the build_bin folder.

 * Force rebuild u-boot

```
bitbake -c clean  u-boot
bitbake u-boot
```

 * Force rebuild rootfs image

```
bitbake -c clean  baylibre-acme-image
bitbake baylibre-acme-image
```

 * Force rebuild kernel

```
bitbake -c clean  linux-yocto-mainline
bitbake linux-yocto-mainline
```


