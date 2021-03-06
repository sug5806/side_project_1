package project.economy_site.utils.oauth2_provider;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public enum CustomOAuth2Provider {
    KAKAO {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(
                    registrationId,
                    ClientAuthenticationMethod.POST,
                    DEFAULT_REDIRECT_URL)
                    .scope("profile")
                    .authorizationUri("https://kauth.kakao.com/oauth/authorize")
                    .tokenUri("https://kauth.kakao.com/oauth/token")
                    .userInfoUri("http://kapi.kakao.com/v2/user/me")
                    .userNameAttributeName("id")
                    .clientName("Kakao");

            return builder;
        }
    };

    private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/login/oauth2/code/{registrationId}";

    protected final ClientRegistration.Builder getBuilder(
            String registrationId,
            ClientAuthenticationMethod method,
            String redirectUri) {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId)
                .clientAuthenticationMethod(method)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(redirectUri);

        return builder;
    }

    public abstract ClientRegistration.Builder getBuilder(String registrationId);
}
