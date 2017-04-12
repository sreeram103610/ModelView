cmake_minimum_required( VERSION 3.4.1)

add_library(modellib SHARED hellojni.cpp)

set (src_dir ../../../../../../Downloads/assimp-master)
set (output_dir ../../../../../../../Downloads/assimp-master/output)
file(MAKE_DIRECTORY ${output_dir})

add_subdirectory(${src_dir} ${output_dir})

include_directories(${src_dir}/include)

target_link_libraries(modellib assimp)