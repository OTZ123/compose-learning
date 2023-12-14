package com.juke.compose.learning.v

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.juke.compose.learning.R
import com.juke.compose.learning.bean.ArticleView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class IconArticleView : ArticleView("图片组件") {
    @Composable
    override fun CreateContent() {
        LazyColumn {
            item {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Lock",
                    tint = Color.Blue
                )
            }

            item {
                Text(text = "不能加载vector类型资源不然会报错")
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_launcher_webp),
                    contentDescription = "R.mipmap.ic_launcher_webp",
                    modifier = Modifier
                        .background(color = Color.Gray)
                )
            }

            item {
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher_webp),
                    contentDescription = "R.mipmap.ic_launcher_webp",
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .size(100.dp),
                    alignment = Alignment.BottomEnd,
                    contentScale = ContentScale.Inside,
                    alpha = .5f
                )
            }

            item {
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher_webp),
                    contentDescription = "R.mipmap.ic_launcher_webp",
                    colorFilter = ColorFilter.tint(Color.Red, BlendMode.Plus)
                )
            }

            item {
                Text(text = "利用Glide加载网络图片")
                val holder = painterResource(id = R.mipmap.ic_launcher_webp)
                var picture by remember {
                    mutableStateOf(holder)
                }

                Image(painter = picture, contentDescription = null)

                if (picture == holder) {//避免重复加载
                    Glide.with(LocalContext.current)
                        .asBitmap()
                        .load("https://img.zcool.cn/community/0179895e4928ffa801216518225ff5.jpg")
                        .into(object : CustomTarget<Bitmap>() {
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?
                            ) {
                                picture = BitmapPainter(resource.asImageBitmap())
                            }

                            override fun onLoadCleared(placeholder: Drawable?) {}
                        })
                }
            }
        }
    }
}