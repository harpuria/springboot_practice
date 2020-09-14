#!/usr/bin/env bash

# 쉬고있는 profile 찾기 : real1 이 사용중이면 real2 는 쉬고, 그 반대면 real1 이 쉬고 있다
function find_idle_profile() {
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_CODE} -ge 400 ] # 400 에러 이상 모두 포함
  then
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  if[ ${CURRENT_PROFILE} == real1 ]
  then
    IDLE_PROFILE=real2
  else
    IDLE_PROFILE=real1
  fi

  echo "${IDLE_PROFILE}"
}

# 쉬고있는 profile의 port 찾기
function find_idle_port(){
  IDLE_PROFILE=$(find_idle_profile)

  if[ ${IDLE_PROFILE} == real1]
  then
    echo "8001"
  else
    echo "8082"
  fi
}
