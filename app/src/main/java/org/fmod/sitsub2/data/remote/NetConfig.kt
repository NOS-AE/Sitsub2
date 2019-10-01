package org.fmod.sitsub2.data.remote

import org.fmod.sitsub2.BuildConfig

const val GITHUB_BASE_URL = "https://github.com/"
const val GITHUB_API_BASE_URL = "https://api.github.com/"

const val OAUTH2_URL = "${GITHUB_BASE_URL}login/oauth/authorize"
const val OAUTH2_SCOPE = "user,repo,gist,notifications"
const val CLIENT_ID = BuildConfig.SITSUB2_CLIENT_ID
const val CLIENT_SECRET = BuildConfig.SITSUB2_CLIENT_SECRET
const val REDIRECT_URL = BuildConfig.SITSUB2_REDIRECT_URL

const val HTTP_TIME_OUT = 32 * 1000L