cmake_minimum_required(VERSION 3.25)
project(rdma VERSION 1.0.1 LANGUAGES C CXX)

add_library(disni)
# set the C++ standard 
set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wno-dev")

add_subdirectory(affinity)
add_subdirectory(verbs)

target_link_libraries(disni verbs affinity)
