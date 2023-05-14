package com.example.homework2.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.homework2.data.PrefsStorage
import com.example.homework2.data.mappers.PostMapper
import com.example.homework2.data.mappers.ProfileMapper
import com.example.homework2.data.model.Post
import com.example.homework2.data.model.Profile
import com.example.homework2.data.pagging.PostPagingSource
import com.example.homework2.data.remote.NanoPostUsernameApiService
import com.example.homework2.data.remote.NanopostApiService
import com.example.homework2.data.remote.NanopostAuthApiService
import com.example.homework2.data.remote.model.ApiResult
import com.example.homework2.data.remote.model.ApiToken
import com.example.homework2.data.remote.model.RegistrationRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: NanopostApiService,
    private val usernameApiService: NanoPostUsernameApiService,
    private val profileMapper: ProfileMapper,
    private val postMapper: PostMapper,
    private val authApiService: NanopostAuthApiService,
) : ProfileRepository {
    override suspend fun getToken(username: String, password: String): ApiToken {
        return authApiService.login(username,password)
    }

    override suspend fun getToken(registrationRequest: RegistrationRequest): ApiToken {
        return authApiService.register(registrationRequest)
    }

    override suspend fun getProfile(profileId: String): Profile {
        return profileMapper.apiToModel(apiService.getProfile(profileId))
    }

    override suspend fun checkUsername(username : String): ApiResult {
        return usernameApiService.checkUsername(username)
    }
    /*
    override suspend fun getProfilePosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(30, enablePlaceholders = false),
            pagingSourceFactory = { PostPagingSource(apiService) },
        ).flow.map { pagingdata ->
            pagingdata.map {
                postMapper.apiToModel(apiService.getProfilePosts(profileId = it.id, count = it.count, offset = it.offset))
            }

        }
    }


     */
}