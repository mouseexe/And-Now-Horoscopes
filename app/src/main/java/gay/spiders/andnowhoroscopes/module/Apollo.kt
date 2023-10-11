package gay.spiders.andnowhoroscopes.module

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

private class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${gay.spiders.andnowhoroscopes.BuildConfig.apikey}")
            .build()
        return chain.proceed(request)
    }
}

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://graphql.contentful.com/content/v1/spaces/2cjieowfbzy3")
    .okHttpClient(
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    )
    .build()