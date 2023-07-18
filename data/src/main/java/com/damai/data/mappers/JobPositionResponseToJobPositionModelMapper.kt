package com.damai.data.mappers

import com.damai.base.BaseMapper
import com.damai.data.responses.JobPositionResponse
import com.damai.domain.models.JobPositionModel

/**
 * Created by damai007 on 18/July/2023
 */
class JobPositionResponseToJobPositionModelMapper : BaseMapper<JobPositionResponse, JobPositionModel>() {

    override fun map(value: JobPositionResponse): JobPositionModel {
        return JobPositionModel(
            id = value.id.orEmpty(),
            title = value.title,
            company = value.company,
            companyUrl = value.companyUrl,
            companyLogo = value.companyLogo,
            location = value.location,
            description = value.description,
            url = value.url,
            howToApply = value.howToApply,
            type = value.type
        )
    }
}