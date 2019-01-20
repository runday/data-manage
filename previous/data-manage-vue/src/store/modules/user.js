import { login } from '@/api/login'
import { getEmployeeInfo } from '@/api/employee'
import { getToken, setToken, removeToken} from '@/utils/auth'

const user = {
  state: {
    token: getToken(),
    passWord:'',
    orgType: '',
    orgId: '',
    organizationName:'',
    realName: '',
    roleList: [],
    resourceList:[]
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    PASSWORD: (state, passWord) => {
      state.passWord = passWord
    },
    ORG_TYPE: (state, orgType) => {
      state.orgType = orgType
    },
    ORG_ID: (state, orgId) => {
      state.orgId = orgId
    },
    ORGANIZATION_NAME: (state, organizationName) => {
      state.organizationName = organizationName
    },
    REAL_NAME: (state, realName) => {
      state.realName = realName
    },
    ROLE_LIST: (state, roleList) => {
      state.roleList = roleList
    },
    RESOURCELIST: (state, resourceList) => {
      state.resourceList = resourceList
    }
  },

  actions: {
    //用户登陆
    login({ commit }, employeeInfo) {
      return new Promise((resolve, reject) => {
        login(employeeInfo.account.trim(), employeeInfo.passWord).then(response => {
          const data = response.data
          if(data.code!=0){
            commit('SET_TOKEN', data.data)
            setToken(data.data)
            resolve()
          }else{
            reject(data.msg)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    //获取登陆用户的信息
    GetEmployeeInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getEmployeeInfo(state.token).then(response => {
          if(response.data&&response.data.data&&response.data.data.roleList&&response.data.data.roleList.length>0&&response.data.data.resourceList&&response.data.data.resourceList.length>0){
            const data = response.data.data
            commit('PASSWORD', data.password)
            commit('ORG_TYPE', data.orgType)
            commit('ORG_ID', data.orgId)
            commit('ORGANIZATION_NAME', data.organizationName)
            commit('REAL_NAME', data.realName)
            commit('ROLE_LIST', data.roleList)
            commit('RESOURCELIST', data.resourceList)
            resolve(data.resourceList)
          }else{
            reject("您没有权限进行登陆，请联系负责人")
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 登出
    LogOut({ commit, state }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },
    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },
  }
}
export default user
