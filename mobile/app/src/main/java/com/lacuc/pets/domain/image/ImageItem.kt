package com.lacuc.pets.domain.image

import com.lacuc.pets.data.group.entity.GroupImage

class ImageItem(val image: GroupImage, val clickListener: (ImageItem) -> Unit) {
    val url = image.url
    val tag = image.tag
}