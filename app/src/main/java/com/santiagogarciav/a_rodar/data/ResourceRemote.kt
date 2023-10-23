//package com.santiagogarciav.a_rodar.data
//
//sealed class ResourceRemote<T>(
//    var data: T? = null,
//    var message: String? = null,
//    var status : Status? = null,
//    var errorCode: Int? = null
//    ){
//    class Success<T>(data: T) : ResourceRemote<T>{data = data, Status = Status.Success}
//
//    class Loading<T>(message: String = "") : ResourceRemote<T>{message = message, Status = Status.Loading}
//
//    class Error<T>(errorCode: Int? = null, message: String? = null) : ResourceRemote<T>{errorCode = errorCode, message = message, Status = Status.Error}
//}
//
//enum class Status{
//    Success{
//       override fun toString(): String{
//           return this.name
//       }
//    },
//    Error{
//        override fun toString(): String{
//            return this.name
//        }
//    },
//    Loading{
//        override fun toString(): String{
//            return this.name
//        }
//    }
//}