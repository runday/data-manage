const getters = {
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  passWord: state => state.user.passWord,
  permission_routers: state => state.permission.routers,
  addRouters: state => state.permission.addRouters,
  orgType: state => state.user.orgType,
  orgId: state => state.user.orgId,
  organizationName: state => state.user.organizationName,
  realName: state => state.user.realName,
  roleList: state => state.user.roleList,
  resourceList: state => state.user.resourceList
}
export default getters
