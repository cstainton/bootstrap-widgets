#!/usr/bin/env bash
set -euo pipefail

root_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "${root_dir}"

pages_dir="${PAGES_OUTPUT_DIR:-${root_dir}/target/pages}"
case "${pages_dir}" in
  "${root_dir}"/target/*) ;;
  *)
    echo "Refusing to clear Pages output outside ${root_dir}/target: ${pages_dir}" >&2
    exit 1
    ;;
esac

showcase_dir="$(find gwt/gwt-bootstrap3-showcase/target -maxdepth 1 -type d -name 'gwt-bootstrap3-showcase-*' -print -quit)"
bootstrap3_fixture_dir="$(find gwt/gwt-bootstrap3-browser-fixtures/target -maxdepth 1 -type d -name 'gwt-bootstrap3-browser-fixtures-*' -print -quit)"
bootstrap5_showcase_dir="$(find gwt/gwt-bootstrap5-showcase/target -maxdepth 1 -type d -name 'gwt-bootstrap5-showcase-*' -print -quit)"

if [[ -z "${showcase_dir}" || -z "${bootstrap3_fixture_dir}" || -z "${bootstrap5_showcase_dir}" ]]; then
  echo "Both compiled GWT showcases and the Bootstrap 3 browser fixtures are required" >&2
  exit 1
fi

rm -rf "${pages_dir}"
mkdir -p "${pages_dir}"
touch "${pages_dir}/favicon.ico"
cp -R "${showcase_dir}/"* "${pages_dir}/"
if [[ -f "${pages_dir}/GwtBootstrap3Demo.html" ]]; then
  cp "${pages_dir}/GwtBootstrap3Demo.html" "${pages_dir}/showcase.html"
fi

mkdir -p "${pages_dir}/bootstrap5"
cp -R "${bootstrap5_showcase_dir}/"* "${pages_dir}/bootstrap5/"

mkdir -p "${pages_dir}/fixtures/gwt-bootstrap3"
cp -R "${bootstrap3_fixture_dir}/"* "${pages_dir}/fixtures/gwt-bootstrap3/"

mkdir -p "${pages_dir}/teavm"
cp -R teavm/teavm-bootstrap3/target/teavm/* "${pages_dir}/teavm/"
cp teavm/teavm-bootstrap3/src/main/resources/teavm.html "${pages_dir}/teavm.html"

# teavm.html is served from the site root, so its relative theme assets live
# beside it rather than under the TeaVM JavaScript directory.
b3_res="gwt/gwt-bootstrap3/src/main/java/org/gwtbootstrap3/client/resource"
mkdir -p "${pages_dir}/bootstrap" "${pages_dir}/themes" "${pages_dir}/fonts" "${pages_dir}/css" "${pages_dir}/vendor"
cp "${b3_res}/css/bootstrap-3.4.1.min.cache.css" "${pages_dir}/bootstrap/"
cp "${b3_res}/css/bootstrap-theme-3.4.1.min.cache.css" "${pages_dir}/bootstrap/"
cp "${b3_res}/css/font-awesome-4.7.0.min.cache.css" "${pages_dir}/css/"
cp "${b3_res}/css/gwt-bootstrap3.cache.css" "${pages_dir}/css/"
cp "${b3_res}"/fonts/* "${pages_dir}/fonts/"
cp gwt/gwt-bootstrap3-themes/src/main/java/org/gwtbootstrap3/themes/client/resource/css/bootswatch-*.css "${pages_dir}/themes/"
cp "${b3_res}/js/jquery-3.7.1.min.cache.js" "${pages_dir}/vendor/"
cp "${b3_res}/js/bootstrap-3.4.1.min.cache.js" "${pages_dir}/vendor/"

b5_res="gwt/gwt-bootstrap5/src/main/java/io/instanto/bootstrap5/client/resource"
mkdir -p "${pages_dir}/teavm5"
cp -R teavm/teavm-bootstrap5/target/teavm/* "${pages_dir}/teavm5/"
cp teavm/teavm-bootstrap5/src/main/resources/teavm-bootstrap5.html "${pages_dir}/teavm-bootstrap5.html"
cp "${b5_res}/js/bootstrap-5.3.8.bundle.min.cache.js" "${pages_dir}/vendor/"
cp "${b5_res}/js/bootstrap-5.3.8.bundle.min.cache.js.map" "${pages_dir}/vendor/"

test -s "${pages_dir}/showcase.html"
test -s "${pages_dir}/bootstrap5/index.html"
test -s "${pages_dir}/fixtures/gwt-bootstrap3/index.html"
test -s "${pages_dir}/fixtures/gwt-bootstrap3/Bootstrap3BrowserFixtures/Bootstrap3BrowserFixtures.nocache.js"
test -s "${pages_dir}/teavm.html"
test -s "${pages_dir}/teavm-bootstrap5.html"
test -s "${pages_dir}/teavm/teavm-bootstrap3-smoke.js"
test -s "${pages_dir}/teavm5/teavm-bootstrap5-smoke.js"
test -s "${pages_dir}/vendor/jquery-3.7.1.min.cache.js"
test -s "${pages_dir}/vendor/bootstrap-3.4.1.min.cache.js"
test -s "${pages_dir}/vendor/bootstrap-5.3.8.bundle.min.cache.js"

echo "Showcase Pages assembled at ${pages_dir}"
