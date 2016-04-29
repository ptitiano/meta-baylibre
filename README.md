# BayLibre meta layer for Poky #

This README file contains information on the contents of the
baylibre layer.

Please see the corresponding sections below for details.

## Layers and sub-layers ##

 * __meta_baylibre__      is the layer for common scripts/classes/ custom recipes.
 * __meta-baylibre-acme__ is the layer for the current beaglebone-based acme board.
 * __meta-baylibre-virt__ is the layer to experiment with qemuarm.

## Fetching and preparing ##

Get the repo with:

 * ACME $> repo init -u https://github.com/BayLibre/manifests.git -m acme/poky-wip.xml
 * ACME $> repo sync

Create a link for the makefile:

 * ACME $> ln -s meta-baylibre/Makefile Makefile

build with:

 * ACME $> make

## Built kernel and images ##

The distro builds a custom kernel pulled from mainline, in
order to have the latest drivers for the power monitoring
chip in use.

### image name ###

<p align="center">
baylibre-acme-image
</p>

### kernel recipe ###

<p align="center">
linux-yocto-mainline
</p>

## Build Configuration ##


```
BB_VERSION        = "1.30.0"
BUILD_SYS         = "x86_64-linux"
NATIVELSBSTRING   = "universal"
TARGET_SYS        = "arm-poky-linux-gnueabi"
MACHINE           = "beaglebone"
DISTRO            = "acme-baylibre"
DISTRO_VERSION    = "2.1"
TUNE_FEATURES     = "arm armv7a vfp  neon        callconvention-hard        cortexa8"
TARGET_FPU        = "hard"
meta              
meta-poky         
meta-yocto-bsp    = "HEAD:a9b503b268e94d311f892fa00c5d6bd9ffdb228e"
meta-python       
meta-networking   
meta-oe           = "HEAD:6fbaf07c2b41ef833a91fe016864d0d1a3815d88"
meta-baylibre     = "master:e05b983ac24585ddbf3982c56d36af8dce2ac579"
meta-ti           = "HEAD:6dea1b68af73cc1c6bcf4c3f780ed6fcce770adb"
meta-systemd      = "HEAD:6fbaf07c2b41ef833a91fe016864d0d1a3815d88"
```
