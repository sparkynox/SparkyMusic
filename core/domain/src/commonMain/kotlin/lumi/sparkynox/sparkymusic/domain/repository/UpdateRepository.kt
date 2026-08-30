package lumi.sparkynox.sparkymusic.domain.repository

import lumi.sparkynox.sparkymusic.domain.data.model.update.UpdateData
import lumi.sparkynox.sparkymusic.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UpdateRepository {
    fun checkForGithubReleaseUpdate(): Flow<Resource<UpdateData>>
    fun checkForFdroidUpdate(): Flow<Resource<UpdateData>>
}