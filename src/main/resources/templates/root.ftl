<#if userContext.gitHubAccessToken??>
    <h3>Logged In</h3>
    <p><a href="?action=repos">View Repos</a></p>
    <p><a href="?action=logout">Log Out</a></p>
<#else>
    <h3>Not logged in</h3>
    <p><a href="?action=login">Log In</a></p>
</#if>
