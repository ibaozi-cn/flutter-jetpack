package one.hundred.lifestyle.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by zzy on 2017/9/15.
 */
@Entity
class DbTest(@PrimaryKey(autoGenerate = true) var id: Long,
             var name: String,
             var code: String,
             var createTime: Date)