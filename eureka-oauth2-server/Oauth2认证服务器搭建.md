本应用作为一个服务，网关层调用该服务，用于获取访问的凭证 AccessToken，进而来调用其他的服务。

OAuth2：

首先
OAuth相关的表中的字段解释：
username ~ password
    1、首次获取AccessToken：
        针对于用户验证，自定义的 CustomUserDetailsService 的 loadUserByUsername 用于验证此用户信息。
        而如果说，username为空，则说明，是在 使用RefreshToken 获取AccessToken
    2、通过refresh_token 获取 AccessToken值
        请求到/oauth/token 映射的参数只有 refresh_token、grant_type而没有username，
        因此导致username只能通过 refresh_token 从数据库表 oauth_refresh_token 表获取 authentication字段(Blob格式),
        然后反序列化该字段得到认证相关信息，即可得到 username。

client_id ~ client_secret
    针对于认证资源的账密。OAuth2将验证是否正确。






